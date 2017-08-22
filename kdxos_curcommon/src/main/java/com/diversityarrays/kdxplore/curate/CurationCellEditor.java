/*
    KDXplore provides KDDart Data Exploration and Management
    Copyright (C) 2015,2016,2017  Diversity Arrays Technology, Pty Ltd.
    
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
package com.diversityarrays.kdxplore.curate;

import java.awt.Component;
import java.util.List;

import org.apache.commons.collections15.Closure;

import com.diversityarrays.kdxplore.data.kdx.CurationData;
import com.diversityarrays.kdxplore.data.kdx.SampleGroup;
import com.diversityarrays.kdxplore.data.kdx.SampleSource;

public interface CurationCellEditor {

    CurationData getCurationData();
    
    void acceptOrSuppressEditedSamples(
            List<CurationCellValue> ccvList,
            String reasonForSuppression,
            Closure<List<EditedSampleInfo>> register);
    
    void acceptOrSuppressValueIgnoringParameters(
            ValueForUnscored valueForUnscored,
            SampleSource source,
            List<CurationCellValue> ccvList,
            String reasonForSuppression,
            Closure<List<EditedSampleInfo>> register);

    void setCurationCellValue(List<CurationCellValue> ccvs);

    void setAllValuesToAccepted(List<CurationCellValue> ccvs, SampleGroup sourceGroup);

    Component getComponent();
}
