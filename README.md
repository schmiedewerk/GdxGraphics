##About
A simple graphics extension for [libGDX](https://github.com/libGDX/libGDX).

Allows for quick prototyping without having to worry about assets just yet.

During the prototyping phase the main focus lies on getting something on the screen.
Therefore the Graphics class was designed in a way that allows to get the act of rendering
out of the way. (Definitely useful when not prototyping too...)

##Quick Example
###Setup
create: graphics = new Graphics(virtualWidth, virtualHeight, maxSpriteCount);

render: graphics.draw();

resize: graphics.onResize(width, height);

dispose: graphics.dispose();

###Usage
Sprite sprite = graphics.sprite();

sprite.layer(1); // higher layer -> drawn on top

sprite.region(pixel); // use 1x1 white pixel TextureRegion

sprite.size(10, 5); // now a 10x5 rectangle

sprite.position(mouseX - 5, mouseY - 2.5f); // place rectangle centered around mouse

sprite.origin(5, 2.5f); // to rotate around the center

sprite.rotation(45); // 45 degrees

sprite.color(.8f, .2f, .2f, 1); // red!

// sprite is now automatically rendered

// after a while, when you don't need the sprite anymore:

sprite.dispose();

##Overview
The core of the extension is divided into two main classes: Graphics and Sprite.

Graphics handles the rendering while Sprite is the render data container.

The main use case is using a 1x1 white texture(region) that can be easily positioned,
resized, rotated and colored. (see quick example above)

However, you can use your regular images just as easily.

##Graphics
- sprite(): allows for quick creation of a sprite
- draw(): handles the sorting, filtering and drawing of all active sprites
- uses a FitViewport to take care of your screen scaling needs
- pools Sprite instances to avoid Garbage Collection!

##Sprite
- layer (int)
- TextureRegion
- visible (boolean)
- position (x,y)
- origin (x,y)
- size (width,height)
- scale (x,y)
- rotation (degrees)
- color (r,g,b,a)
- dispose(): removes the Sprite from the rendering and returns it to the Sprite pool

##Building on it
The Sprite and Graphics class are both kept to their essential core.
This allows for quick and easy additions and variations depending on your use case.

(examples: in progress)

#License
This extension is licensed under the [Apache 2 License](http://www.apache.org/licenses/LICENSE-2.0.html), meaning you can use it free of charge, without strings attached in commercial and non-commercial projects.