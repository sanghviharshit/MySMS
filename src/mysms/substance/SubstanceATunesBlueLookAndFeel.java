/*
 * aTunes 1.12.0
 * Copyright (C) 2006-2009 Alex Aranda, Sylvain Gaudard, Thomas Beckers and contributors
 *
 * See http://www.atunes.org/wiki/index.php?title=Contributing for information about contributors
 *
 * http://www.atunes.org
 * http://sourceforge.net/projects/atunes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package mysms.substance;

import org.jvnet.substance.SubstanceLookAndFeel;

/**
 * The Class SubstanceATunesLookAndFeel.
 */
public class SubstanceATunesBlueLookAndFeel extends SubstanceLookAndFeel {

	public SubstanceATunesBlueLookAndFeel()
    {
		super(new SubstanceATunesBlueSkin());
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3907225219153995877L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jvnet.substance.SubstanceLookAndFeel#getID()
	 */
	@Override
	public String getID() {
		return "Substance aTunes Blue";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jvnet.substance.SubstanceLookAndFeel#getName()
	 */
	@Override
	public String getName() {
		return "Substance aTunes Blue";
	}

}
