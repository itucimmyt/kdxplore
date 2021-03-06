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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diversityarrays.kdsmart.db.entities.Plot;
import com.diversityarrays.kdsmart.db.entities.TraitInstance;

public class SelectedInfo {
    public final PlotsByTraitInstance plotsByTraitInstance = new PlotsByTraitInstance();
    public final Map<TraitInstance, List<CurationCellValue>> ccvsByTraitInstance = new HashMap<>();

    public void addPlot(TraitInstance ti, Plot plot) {
        plotsByTraitInstance.addPlot(ti, plot);
    }
    
    public void addCcv(TraitInstance ti, CurationCellValue ccv) {
        List<CurationCellValue> list = ccvsByTraitInstance.get(ti);
        if (list == null) {
            list = new ArrayList<>();
            ccvsByTraitInstance.put(ti, list);
        }
        list.add(ccv);
    }
}
