/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pmg.mediasupport.mp3support;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.scene.media.Media;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "org.pmg.mediasupport.mp3support.ViewMP3Action"
)
@ActionRegistration(
        iconBase = "org/pmg/mediasupport/mp3support/MP3Icon.png",
        displayName = "#CTL_ViewMP3Action"
)
@ActionReference(path = "Loaders/audio/mp3/Actions", position = 0, separatorAfter = 50)
@Messages("CTL_ViewMP3Action=View")
public final class ViewMP3Action implements ActionListener {

    private final DataObject context;

    public ViewMP3Action(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String path = "file:///"+context.files().iterator().next().getPath()
                .replace(" ", "%20");
        MP3Player player = new MP3Player(path);
        player.setVisible(true);
    }
}
