/*      PANDA -- a simple transaction monitor
                                                                                
Copyright (C) 1998-1999 Ogochan.
			  2000-2003 Ogochan & JMA (Japan Medical Association).
                                                                                
This module is part of PANDA.
                                                                                
		PANDA is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY.  No author or distributor accepts responsibility
to anyone for the consequences of using it or for whether it serves
any particular purpose or works at all, unless he says so in writing.
Refer to the GNU General Public License for full details.
                                                                                
		Everyone is granted permission to copy, modify and redistribute
PANDA, but only under the conditions described in the GNU General
Public License.  A copy of this license is supposed to have been given
to you along with PANDA so you can know your rights and
responsibilities.  It should be in a file named COPYING.  Among other
things, the copyright notice and this notice must be preserved on all
copies.
*/

package org.montsuqi.monsia.builders;

import java.awt.Component;
import java.awt.Container;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;

import org.montsuqi.monsia.ChildInfo;
import org.montsuqi.monsia.Interface;
import org.montsuqi.monsia.SignalInfo;
import org.montsuqi.monsia.WidgetInfo;

class ComboBuilder extends ContainerBuilder {
	Component buildSelf(Interface xml, Container parent, WidgetInfo info) {
		Component widget = super.buildSelf(xml, parent, info);
		JComboBox combo = (JComboBox)widget;
		combo.setEditable(true);
		return widget;
	}

	void buildChildren(Interface xml, Container parent, WidgetInfo info) {
		JComboBox combo = (JComboBox)parent;
		if (info.getChildren().size() != 1) {
			throw new WidgetBuildingException(Messages.getString("WidgetBuilder.only_one_child_is_allowed_in_Combo")); //$NON-NLS-1$
		}
		ChildInfo cInfo = info.getChild(0);
		WidgetInfo wInfo = cInfo.getWidgetInfo();
		if ( ! "Entry".equals(wInfo.getClassName())) { //$NON-NLS-1$
			throw new WidgetBuildingException(Messages.getString("WidgetBuilder.not_a_Entry_widget")); //$NON-NLS-1$
		}
		Component editor = combo.getEditor().getEditorComponent();
		xml.setLongName(wInfo.getLongName(), editor);
		xml.setComboMap(combo, editor);
		List signals = wInfo.getSignals();
		Iterator i = signals.iterator();
		while (i.hasNext()) {
			SignalInfo sInfo = (SignalInfo)i.next();
			info.addSignalInfo(sInfo);
		}
	}
}