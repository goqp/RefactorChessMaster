# RefactorChessMaster
Project to refactor an existing open source Java application according to OOP principles.

I when say "refactor" I mean REPLACE. I thought originally that the code could be modified to make it to conform to OOP. However, having attempted to do so I can see that its impossible. The source code is entirely procedural, in fact, it's basically just "C in Java" as some well known persons like to say. You can't change anything without breaking the whole program. Therefore...

THE FIRST GOAL OF THIS PROJECT IS TO ACHIEVE THE EXACT SAME BEHAVIOR OF THE ORIGINAL PROGRAM USING OOP INSTEAD OF THE SOURCE CODE.
THE SECOND GOAL IS TO COMPARE THE CORRESPONDING OOP AND PROCEDURAL CLASSES IN ORDER TO DETERMINE THEIR KEY DIFFERENCES.
THE THIRD GOAL IS TO EXPLAIN WHY THE ELIMINATED CLASSES (IF ANY) WERE REMOVED.
THE FOURTH GOAL IS TO EXPLAIN WHY NEW CLASSES (IF ANY) WERE ADDED.

Downloading the files and getting them to work:

When you download the source files the java classes need to go into the root package of your neat beans project or whatever IDE you are using. The image files should go into a package one level down called "chessImages". The pieces using the gifs access them with the following function call: getClass().getResource("chessImages/[putTheImageNameHere].gif")

Note on authenticity of the source code:

I actually changed the source code slightly to use the above call rather than "new ImageIcon([filePathHere)" or "createImageIcon([Stringpath], [Stringdescription])". It wouldn't work otherwise. See: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html for an explanation.
