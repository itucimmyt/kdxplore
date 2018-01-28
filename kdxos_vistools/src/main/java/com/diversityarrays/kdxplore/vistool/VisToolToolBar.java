/*
    KDXplore provides KDDart Data Exploration and Management
    Copyright (C) 2015,2016,2017,2018  Diversity Arrays Technology, Pty Ltd.
    
    KDXplore may be redistributed and may be modified under the terms
    of the GNU General Public License as published by the Free Software
    Foundation, either version 3 of the License, or (at your option)
    any later version.
    
    KDXplore is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with KDXplore.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.diversityarrays.kdxplore.vistool;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;

public class VisToolToolBar extends JToolBar {

	public final JCheckBox keepOnTop;
	public final JButton cameraButton;
	public final JButton refreshButton;
	
	public VisToolToolBar(
			JCheckBox keepOnTop, 
			JButton cameraButton,
			JButton refreshButton) 
	{
		this.keepOnTop = keepOnTop;
		this.cameraButton = cameraButton;
		this.refreshButton = refreshButton;
		
		if (keepOnTop != null) {
			add(keepOnTop);
		}
		
		if (cameraButton != null) {
			add(cameraButton);
		}
		
		if (refreshButton != null) {
			add(Box.createHorizontalStrut(4));
			add(refreshButton);
		}
	}
}
