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
package com.diversityarrays.kdxplore.fieldlayout;

public class DesignParams {
    public final int entryCount;
    public final double percentSpatials;
    public final int nSpatials;
    public final int plotsPerReplicate;
    public final int width;
    public final int height;
    public final int replicateCount;

    public DesignParams(int nEntries, double pct, int repCount) {
        entryCount = nEntries;
        percentSpatials = pct;
        replicateCount = repCount;

        nSpatials = (int) Math.floor(entryCount * percentSpatials / 100.0);
        plotsPerReplicate = entryCount + nSpatials;

        width = (int) Math.ceil(Math.sqrt(plotsPerReplicate));
        int nRows = (int) Math.floor((plotsPerReplicate * 1.0) / width);
        while ((nRows * width) < plotsPerReplicate) {
            ++nRows;
        }
        height = nRows;
    }
}