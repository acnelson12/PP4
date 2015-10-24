# PP4
an attempt to rewrite Torsten Bronger’s PP3 program for generating star charts

Currently, this project is not remotely close to being usable.
The primary goal is to extend the capability of PP3.

Firstly, I am working on breaking the program up into smaller pieces to follow a more
object-oriented design.  As I am most proficient in Java, I am starting the work in
Java.  Perhaps it will later be ported to C++, but for now, I want to work with it on
a more conceptual level.  This is what I am currently in the process of doing, and code
is likely to change dramatically as I find better ways to do things and the algorithms
evolve.  Because of this, don’t be too quick to criticize.  There are going to be some
messy things.  I try to pay a lot of attention to code-correctness, and the messy things
are often a result of my finding a “more correct” way of doing something which hasn’t
been fully implemented.

Now, I would like to address Torsten Bronger’s wishlist for PP3.

“Very faint stars, i.e. stars fainter than ’faintest_star_disk_magnitude’, should be
removed from the map automatically, if they overlap with a label.  It shouldn’t matter
whether the label was implicitly created or user-defined.”

This will probably be one of the last things I worry about.  Actually, some of the
ideas I have may prevent this issue from ever occurring in the first place.

“An ephemerides module that allows to insert the Sun, the Moon, and the planets on the
map, for a given date, time, and observation location on Earth....”

This is definitely going to be a priority.  I do want to handle stars a bit differently,
and this might allow for generation of planets and such to become much more simple.

“More map projections, in particular a real isogonal one.”

Honestly, at the moment, I have no idea what that is.  I’ll deal with it later.  Perhaps
I can write the program so that you can easily add in as many map projections as you want.

“The resolution of the Milky Way should be determined in a way that TeX’s memory doesn’t
overflow.  Maybe one should switch to contour plots instead of dot patterns for the
Milky Way.”

This is my top priority.  My first thought when looking at the structure of PP3 was actually
“Wouldn’t contour plots be more efficient?”.  However, I am currently thinking that the
use of contour plots would be short-term gain, but long-term loss.  The reason for this is
that the real Milky Way is composed of stars, which can be considered points.  It seems
that to generate a genuine Milky Way, it would be best if one could use the actual stars.
I am aware that this would require potentially massive amounts of data.  I don’t care.
At least, that is the attitude with which I am approaching this.  No matter what, the
program needs to be able to handle massive amounts of data.  The current plan for preventing
the memory overflow is to break the generated chart into layers which can be compiled
separately and then merged into the final chart.

“Create little bitmaps of all labels in order to determine kerning parameters for them.”

This is also low on my priority list.

“PP3 should become able to deal with large data bases.”

I basically addressed this above.

“The star names list of Celestia or something like that should be used for identifying stars
in input scripts.”

I’d like to rework the entire way that the program identifies stars.  I am beginning to
think that the best way to deal with this is to basically have the star names (including
the catalog numbers) be aliases for locations in the sky.  The reason for this is that there
are stars like Sirius which are actually systems of multiple stars.  This could simplify the
process of supplying new catalogs to the program since you would not have to go through and
check for duplicate listings in other catalogs and whether the other catalogs distinguish
between the individual stars of a star system, etc.  The actual stars would be drawn from a
separate list of stars containing the data of magnitude, color, location, etc.

“Besides that, I’d like to re-structure the internals of PP3 because in some respect the code
is not well maintainable and expandable.  For the long-term future, it may be worth thinking
about using Guile as the scripting language.”

Okay, not to be mean or anything, but....
Yeah, it definitely was not well-maintainable or expandable...or readable, for that matter.
Absolutely nothing was indented in the source code I found.  Good grief.
I was hoping to avoid re-structuring the internals, but it quickly became apparent that
there was no other option but to basically rewrite the program.
About using Guile, I really don’t feel like dealing with that right now.

One of the first things I want to change about the program is its use of PSTricks.  I like
pdfLaTeX.  Tikz seems to be much more versatile.

I also want to get rid of the use of different file formats for the stars file, the nebulae
file, the Milky Way file, etc.  Everything should use one format; for example, the stars file
will be a PP4 script file containing drawing commands for stars.  If you want to include star
drawing commands in the main script file, this should be valid, though I’m not sure why you
might want to do this.  This approach will make it easier to modularize scripts in any way
desired.

Something I am considering is the idea of using gradients to generate more realistic stars.
This may be the best way to handle plotting the Milky Way.

I would definitely like to include something for drawing HII regions.  This could create some
very beautiful maps.

Using the aliases approach for star names, I believe it should also be possible to refer to
stars using the Bayer designation or even the proper name.  This could greatly improve script
readability, writability, and maintainability.
