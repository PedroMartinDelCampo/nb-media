/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pmg.mediasupport.mp3support.filerecognition;

import java.io.IOException;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_MP3_LOADER=Files of MP3"
})
@MIMEResolver.ExtensionRegistration(
    displayName="#LBL_MP3_LOADER",
    mimeType="audio/mp3",
    extension={ "mp3", "MP3" }
)
@DataObject.Registration(
    mimeType = "audio/mp3", 
    iconBase = "org/pmg/mediasupport/mp3support/filerecognition/MP3Icon.png",
    displayName="#LBL_MP3_LOADER",
    position=300
)
@ActionReferences({
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="System", id="org.openide.actions.OpenAction"),
        position=100, 
        separatorAfter=200
    ),
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="Edit", id="org.openide.actions.CutAction"),
        position=300
    ),
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="Edit", id="org.openide.actions.CopyAction"),
        position=400,
        separatorAfter=500
    ),
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="Edit", id="org.openide.actions.DeleteAction"),
        position=600
    ),
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="System", id="org.openide.actions.RenameAction"),
        position=700,
        separatorAfter=800
    ),
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="System", id="org.openide.actions.SaveAsTemplateAction"),
        position=900,
        separatorAfter=1000
    ),
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="System", id="org.openide.actions.FileSystemAction"),
        position=1100,
        separatorAfter=1200
    ),
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="System", id="org.openide.actions.ToolsAction"),
        position=1300
    ),
    @ActionReference(
        path="Loaders/audio/mp3/Actions", 
        id=@ActionID(category="System", id="org.openide.actions.PropertiesAction"),
        position=1400
    )
})
public class MP3DataObject extends MultiDataObject {

    public MP3DataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("audio/mp3", false);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

}
