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
package com.diversityarrays.kdxplore.field;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.collections15.Closure;

import com.diversityarrays.kdsmart.scoring.PlotsPerGroup;
import com.diversityarrays.util.Check;
import com.diversityarrays.util.OrOrTr;
import com.diversityarrays.util.Orientation;
import com.diversityarrays.util.Origin;
import com.diversityarrays.util.Traversal;
import com.diversityarrays.util.VisitOrder2D;

public class CollectionPathPanel extends JPanel {

    private VisitOrder2D visitOrder = VisitOrder2D.LL_UP_SERPENTINE;
    private PlotsPerGroup plotsPerGroup = PlotsPerGroup.ONE;

    private final Closure<Void> onChange = new Closure<Void>() {
        @Override
        public void execute(Void arg0) {
            updateVisitOrderChosen();
            if (onChoiceChanged != null) {
                onChoiceChanged.accept(visitOrder, plotsPerGroup);
            }
        }
    };

    private final OriginDirectionTraversalChoicePanel odtChoicePanel = new OriginDirectionTraversalChoicePanel(onChange);

    private final JComboBox<PlotsPerGroup> plotsPerGroupChoice = new JComboBox<>(PlotsPerGroup.values());

    private final BiConsumer<VisitOrder2D, PlotsPerGroup> onChoiceChanged;

    public CollectionPathPanel(
            boolean wantPlotsPerGroup,
            BiConsumer<VisitOrder2D, PlotsPerGroup> onChoiceChanged)
    {
        this(wantPlotsPerGroup, onChoiceChanged, null);
    }

    public CollectionPathPanel(
            boolean wantPlotsPerGroup,
            BiConsumer<VisitOrder2D, PlotsPerGroup> onChoiceChanged,
            List<? extends OrOrTr> onlyThese)
    {
        super(new BorderLayout());

        plotsPerGroupChoice.setSelectedItem(plotsPerGroup);
        odtChoicePanel.setOrOrTr(visitOrder);

        this.onChoiceChanged = onChoiceChanged;

        if (wantPlotsPerGroup) {
            plotsPerGroupChoice.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    plotsPerGroup = (PlotsPerGroup) plotsPerGroupChoice.getSelectedItem();
                    onChoiceChanged.accept(visitOrder, plotsPerGroup);
                }
            });

            Box top = Box.createHorizontalBox();
            top.add(new JLabel("Plots Per Group:"));
            top.add(plotsPerGroupChoice);
            top.add(Box.createHorizontalGlue());

            add(top, BorderLayout.NORTH);
        }

        if (! Check.isEmpty(onlyThese)) {
            OrOrTr first = onlyThese.get(0);

            odtChoicePanel.setOnlyAllow(onlyThese.toArray(new OrOrTr[onlyThese.size()]));

            String msg;
            if (onlyThese.size() == 1) {
                msg = "<HTML>For now, only supporting:<BR>" + onlyThese.get(0).toString();
            }
            else {
                msg = onlyThese.stream()
                        .map(oot -> oot.toString())
                        .collect(Collectors.joining("<BR>", "<HTML>For now, only supporting:<BR>", ""));
            }

            JLabel label = new JLabel(msg, JLabel.CENTER);
            label.setForeground(Color.RED);
            add(label, BorderLayout.SOUTH);
        }

        add(odtChoicePanel, BorderLayout.CENTER);
    }

    public void setOrOrTr(OrOrTr ort, PlotsPerGroup ppg) {

        updateVisitOrderChosen(ort.getOrigin(),
                ort.getOrientation(),
                ort.getTraversal());

        odtChoicePanel.setOrOrTr(ort);

        plotsPerGroup = ppg;
    }


    private void updateVisitOrderChosen() {
        Traversal traversal = odtChoicePanel.getTraversal();
        Origin origin = odtChoicePanel.getOrigin();
        Orientation orientation = odtChoicePanel.getOrientation();
        updateVisitOrderChosen(origin, orientation, traversal);
    }

    private void updateVisitOrderChosen(Origin origin, Orientation orientation, Traversal traversal) {

        switch (origin) {
        case LOWER_LEFT:
            switch (orientation) {
            case HORIZONTAL:
                visitOrder = Traversal.ONE_WAY==traversal
                    ? VisitOrder2D.LL_RIGHT_ZIGZAG
                    : VisitOrder2D.LL_RIGHT_SERPENTINE;
                break;
            case VERTICAL:
                visitOrder = Traversal.ONE_WAY==traversal
                ? VisitOrder2D.LL_UP_ZIGZAG
                : VisitOrder2D.LL_UP_SERPENTINE;
                break;
            default:
                break;
            }
            break;

        case LOWER_RIGHT:
            switch (orientation) {
            case HORIZONTAL:
                visitOrder = Traversal.ONE_WAY==traversal
                    ? VisitOrder2D.LR_LEFT_ZIGZAG
                    : VisitOrder2D.LR_LEFT_SERPENTINE;
                break;
            case VERTICAL:
                visitOrder = Traversal.ONE_WAY==traversal
                ? VisitOrder2D.LR_UP_ZIGZAG
                : VisitOrder2D.LR_UP_SERPENTINE;
                break;
            default:
                break;
            }
            break;

        case UPPER_LEFT:
            switch (orientation) {
            case HORIZONTAL:
                visitOrder = Traversal.ONE_WAY==traversal
                    ? VisitOrder2D.UL_RIGHT_ZIGZAG
                    : VisitOrder2D.UL_RIGHT_SERPENTINE;
                break;
            case VERTICAL:
                visitOrder = Traversal.ONE_WAY==traversal
                ? VisitOrder2D.UL_DOWN_ZIGZAG
                : VisitOrder2D.UL_DOWN_SERPENTINE;
                break;
            default:
                break;
            }

            break;

        case UPPER_RIGHT:
            switch (orientation) {
            case HORIZONTAL:
                visitOrder = Traversal.ONE_WAY==traversal
                    ? VisitOrder2D.UR_LEFT_ZIGZAG
                    : VisitOrder2D.UR_LEFT_SERPENTINE;
                break;
            case VERTICAL:
                visitOrder = Traversal.ONE_WAY==traversal
                ? VisitOrder2D.UR_DOWN_ZIGZAG
                : VisitOrder2D.UR_DOWN_SERPENTINE;
                break;
            default:
                break;
            }

            break;
        default:
            break;
        }
    }
}
