/*
    Aww Gallery
    Copyright (C) 2017  Nicholas Doglio

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nicholasdoglio.data.model.about

/**
 * @author Nicholas Doglio
 *
 * Base object for information that will be displayed in the About screen RecyclerView
 *
 * @param imageID: drawable asset for icons in About screen
 * @param contentName: String that will be the displayed text for each About item
 * @param contentLink: URL that will be launched upon click of each about item
 */
data class AboutInfo(
    val imageID: Int,
    val contentName: String,
    val contentLink: String
)