# RefactorChessMaster
Project to refactor an existing open source Java application according to OOP principles.

When you download the source files the java classes need to go into the root package of your neat beans project or whatever IDE you are using. The image files should go into a package one level down called "chessImages". The pieces using the gifs access them with the following function call:

getClass().getResource("chessImages/[putTheImageNameHere].gif")

I actually changed the source code slightly to use the above call rather than "new ImageIcon([filePathHere)" or "createImageIcon([Stringpath], [Stringdescription])". It wouldn't work otherwise. See: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html for an explanation.
