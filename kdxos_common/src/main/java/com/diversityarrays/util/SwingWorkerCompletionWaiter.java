/*
    KDXplore provides KDDart Data Exploration and Management
    Copyright (C) 2015,2016  Diversity Arrays Technology, Pty Ltd.

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
package com.diversityarrays.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.SwingWorker;

import org.apache.commons.collections15.Closure;

public class SwingWorkerCompletionWaiter implements PropertyChangeListener {
    private JDialog dialog;
	private Closure<JDialog> onComplete;

    public SwingWorkerCompletionWaiter(JDialog dialog, Closure<JDialog> onComplete) {
        this.dialog = dialog;
        this.onComplete = onComplete;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName()) //$NON-NLS-1$
                && SwingWorker.StateValue.DONE == event.getNewValue()) 
        {
        	onComplete.execute(dialog);
        }
    }
}