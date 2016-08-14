package science.freeabyss.hulk.common;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.clients.UserStoreClient;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.type.*;
import com.evernote.thrift.TException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

/**
 * 印象笔记的操作
 * Created by abyss on 04/27/16.
 */
public final class EvernoteUtil {
    public static final String AUTH_TOKEN = "";

    private UserStoreClient userStore = null;
    private NoteStoreClient noteStore = null;
    private String newNoteGuid;


    /**
     * 初始化 笔记本
     *
     * @param token token
     * @throws TException
     * @throws EDAMSystemException
     * @throws EDAMUserException
     */
    public EvernoteUtil(String token) throws TException, EDAMSystemException, EDAMUserException {
        // Set up the UserStore client and check that we can speak to the server
        EvernoteAuth evernoteAuth = new EvernoteAuth(EvernoteService.YINXIANG, token);
        ClientFactory factory = new ClientFactory(evernoteAuth);
        userStore = factory.createUserStoreClient();
        boolean versionOk = userStore.checkVersion("EvernoteUtil Demo (Java)",
                com.evernote.edam.userstore.Constants.EDAM_VERSION_MAJOR,
                com.evernote.edam.userstore.Constants.EDAM_VERSION_MINOR);
        if (!versionOk) {
            System.err.println("Incompatible EvernoteUtil client protocol version");
            System.exit(1);
        }
        noteStore = factory.createNoteStoreClient();
    }

    /**
     * 获得笔记本列表
     *
     * @return 笔记本列表
     * @throws TException
     * @throws EDAMUserException
     * @throws EDAMSystemException
     */
    public List<Notebook> listNotebooks() throws TException, EDAMUserException, EDAMSystemException {
        List<Notebook> evernotebooks = noteStore.listNotebooks();
        for (Notebook book : evernotebooks) {
            System.out.println("===========");
            System.out.println("guid:" + book.getGuid());
            System.out.println("name:" + book.getName());

            System.out.println("updateSequenceNum:" + book.getUpdateSequenceNum());
            System.out.println("===========");
        }
        return evernotebooks;

    }

    public List<LinkedNotebook> listLinkedNotebooks() throws EDAMUserException, EDAMSystemException, TException, EDAMNotFoundException {
        List<LinkedNotebook> notebooks = noteStore.listLinkedNotebooks();
        for (LinkedNotebook notebook : notebooks) {
            System.out.println("username:" + notebook.getUsername());
            System.out.println("sharename:" + notebook.getShareName());
        }
        return notebooks;
    }

    /**
     * 获得指定笔记本的笔记,不包含笔记的内容
     *
     * @param notebook 指定的笔记本
     * @return 返回笔记列表
     * @throws EDAMUserException
     * @throws EDAMSystemException
     * @throws TException
     * @throws EDAMNotFoundException
     */
    public List<Note> listNotes(Notebook notebook) throws EDAMUserException, EDAMSystemException, TException, EDAMNotFoundException {
        NoteFilter filter = new NoteFilter();
        filter.setNotebookGuid(notebook.getGuid());
        filter.setOrder(NoteSortOrder.CREATED.getValue());
        filter.setAscending(true);

        NoteList noteList = null;
        noteList = noteStore.findNotes(filter, 0, 100);
        List<Note> notes = noteList.getNotes();
        return notes;
    }

    /**
     * 获得笔记的内容
     *
     * @param note 指定的笔记
     * @return 返回笔记内容
     */
    public String getNoteContent(Note note) throws EDAMUserException, EDAMSystemException, TException, EDAMNotFoundException {
        Note dataNote = noteStore.getNote(note.getGuid(), true, true, false, false);
        return dataNote.getContent();
    }


    /**
     * Create a new note containing a little text and the EvernoteUtil icon.
     */
    private void createNote() throws Exception {
        // To create a new note, simply create a new YXNote object and fill in
        // attributes such as the note's title.
        Note note = new Note();
        note.setTitle("Test note from EDAMDemo.java");

        String fileName = "enlogo.png";
        String mimeType = "image/png";

        // To include an attachment such as an image in a note, first create a
        // Resource
        // for the attachment. At a minimum, the Resource contains the binary
        // attachment
        // data, an MD5 hash of the binary data, and the attachment MIME type.
        // It can also
        // include attributes such as filename and location.
        Resource resource = new Resource();
        resource.setData(readFileAsData(fileName));
        resource.setMime(mimeType);
        ResourceAttributes attributes = new ResourceAttributes();
        attributes.setFileName(fileName);
        resource.setAttributes(attributes);

        // Now, add the new Resource to the note's list of resources
        note.addToResources(resource);

        // To display the Resource as part of the note's content, include an
        // <en-media>
        // tag in the note's ENML content. The en-media tag identifies the
        // corresponding
        // Resource using the MD5 hash.
        String hashHex = bytesToHex(resource.getData().getBodyHash());

        // The content of an EvernoteUtil note is represented using EvernoteUtil Markup
        // Language
        // (ENML). The full ENML specification can be found in the EvernoteUtil API
        // Overview
        // at http://dev.evernote.com/documentation/cloud/chapters/ENML.php
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">"
                + "<en-note>"
                + "<span style=\"color:green;\">Here's the EvernoteUtil logo:</span><br/>"
                + "<en-media type=\"image/png\" hash=\"" + hashHex + "\"/>"
                + "</en-note>";
        note.setContent(content);

        // Finally, send the new note to EvernoteUtil using the createNote method
        // The new YXNote object that is returned will contain server-generated
        // attributes such as the new note's unique GUID.
        Note createdNote = noteStore.createNote(note);
        newNoteGuid = createdNote.getGuid();

        System.out.println("Successfully created a new note with GUID: "
                + newNoteGuid);
        System.out.println();
    }

    /**
     * Search a user's notes and display the results.
     */
    private void searchNotes() throws Exception {
        // Searches are formatted according to the EvernoteUtil search grammar.
        // Learn more at
        // http://dev.evernote.com/documentation/cloud/chapters/Searching_notes.php

        // In this example, we search for notes that have the term "EDAMDemo" in
        // the title.
        // This should return the sample note that we created in this basic app.
        String query = "intitle:EDAMDemo";

        // To search for notes with a specific tag, we could do something like
        // this:
        // String query = "tag:tagname";

        // To search for all notes with the word "elephant" anywhere in them:
        // String query = "elephant";

        NoteFilter filter = new NoteFilter();
        filter.setWords(query);
        filter.setOrder(NoteSortOrder.UPDATED.getValue());
        filter.setAscending(false);

        // Find the first 50 notes matching the search
        System.out.println("Searching for notes matching query: " + query);
        NoteList notes = noteStore.findNotes(filter, 0, 50);
        System.out.println("Found " + notes.getTotalNotes() + " matching notes");

        Iterator<Note> iter = notes.getNotesIterator();
        while (iter.hasNext()) {
            Note note = iter.next();
            System.out.println("YXNote: " + note.getTitle());

            // YXNote objects returned by findNotes() only contain note attributes
            // such as title, GUID, creation date, update date, etc. The note
            // content
            // and binary resource data are omitted, although resource metadata
            // is included.
            // To get the note content and/or binary resources, call getNote()
            // using the note's GUID.
            Note fullNote = noteStore.getNote(note.getGuid(), true, true, false,
                    false);
            System.out.println("YXNote contains " + fullNote.getResourcesSize()
                    + " resources");
            System.out.println();
        }
    }

    /**
     * Update the tags assigned to a note. This method demonstrates how only
     * modified fields need to be sent in calls to updateNote.
     */
    private void updateNoteTag() throws Exception {
        // When updating a note, it is only necessary to send EvernoteUtil the
        // fields that have changed. For example, if the YXNote that you
        // send via updateNote does not have the resources field set, the
        // EvernoteUtil server will not change the note's existing resources.
        // If you wanted to remove all resources from a note, you would
        // set the resources field to a new List<Resource> that is empty.

        // If you are only changing attributes such as the note's title or tags,
        // you can save time and bandwidth by omitting the note content and
        // resources.

        // In this sample code, we fetch the note that we created earlier,
        // including
        // the full note content and all resources. A real application might
        // do something with the note, then update a note attribute such as a
        // tag.
        Note note = noteStore.getNote(newNoteGuid, true, true, false, false);

        // Do something with the note contents or resources...

        // Now, update the note. Because we're not changing them, we unset
        // the content and resources. All we want to change is the tags.
        note.unsetContent();
        note.unsetResources();

        // We want to apply the tag "TestTag"
        note.addToTagNames("TestTag");

        // Now update the note. Because we haven't set the content or resources,
        // they won't be changed.
        noteStore.updateNote(note);
        System.out.println("Successfully added tag to existing note");

        // To prove that we didn't destroy the note, let's fetch it again and
        // verify that it still has 1 resource.
        note = noteStore.getNote(newNoteGuid, false, false, false, false);
        System.out.println("After update, note has " + note.getResourcesSize()
                + " resource(s)");
        System.out.println("After update, note tags are: ");
        for (String tagGuid : note.getTagGuids()) {
            Tag tag = noteStore.getTag(tagGuid);
            System.out.println("* " + tag.getName());
        }

        System.out.println();
    }

    /**
     * Helper method to read the contents of a file on disk and create a new Data
     * object.
     */
    private static Data readFileAsData(String fileName) throws Exception {
        String filePath = "/Users/abyss/Downloads/evernote-sdk-java-master/sample/client" + File.separator + fileName;
        // Read the full binary contents of the file
        FileInputStream in = new FileInputStream(filePath);
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        byte[] block = new byte[10240];
        int len;
        while ((len = in.read(block)) >= 0) {
            byteOut.write(block, 0, len);
        }
        in.close();
        byte[] body = byteOut.toByteArray();

        // Create a new Data object to contain the file contents
        Data data = new Data();
        data.setSize(body.length);
        data.setBodyHash(MessageDigest.getInstance("MD5").digest(body));
        data.setBody(body);

        return data;
    }

    /**
     * Helper method to convert a byte array to a hexadecimal string.
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte hashByte : bytes) {
            int intVal = 0xff & hashByte;
            if (intVal < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(intVal));
        }
        return sb.toString();
    }
}
