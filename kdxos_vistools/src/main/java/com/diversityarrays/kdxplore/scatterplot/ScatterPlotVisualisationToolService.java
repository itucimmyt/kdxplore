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
package com.diversityarrays.kdxplore.scatterplot;

import com.diversityarrays.kdxplore.vistool.VisualisationTool;
import com.diversityarrays.kdxplore.vistool.VisualisationToolService;

public class ScatterPlotVisualisationToolService implements VisualisationToolService {
    @Override
    public VisualisationTool createVisualisationTool(VisToolParams params) {
        return new ScatterPlotVisualisationTool(params);
    }
}
