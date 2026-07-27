# SpaceGame

The game I created for my high school capstone. Around Y2K.



## The Game

Learning to program in High School, one of my immediate goals was to produce a game like Warcraft II. I hadn't had any formal Java instruction yet and very little programming instruction in general and just slogged through the best I could.



##### The Theme

I remember I didn't want to deal with a lot of the tedious issues I knew Warcraft had to contend with. So I figure out that: ships floating in space wouldn't collide, vague circular shapes could represent them without needing rotation, no melee combat had to be lined up properly and in fact lasers would avoid having to track arrows and other missiles. Hence the space theme.



##### The Art

I had been taking some computer art classes where I learned to make animated gifs and obviously had to have gratuitous animations everywhere. I think these silly little illustrations were all created with Island School's fully licensed Adobe something or other.



##### The Units

There were 3 tiers of tech in WC2 so I tried to make three tiers of tech here. I also saw that it was the minimum that would be needed for a properly balanced rock paper scissors game:

* Battleships would outlast and outgun Fighters;
* SpaceGuns would destroy Batteships from distance;
* Fighter swarms would overwhelm SpaceGuns.

Then the formation tactics I loved from "Myth" would become relevant. But I never got around to balancing these things, I suspect the big impact of seeing





## The Capstone

I was in 11th grade? but had a slot open for "High School Capstone" class. This game was nearing completion, and yet another game that nobody ever played would make for a poor capstone. I decided I my capstone would be to find an excuse to make people play it. I think I had already put positive, subliminal statements in another [high school game project](https://github.com/TealCoder/RoadKill) and wanted to know if they worked. So I figured I'd use a psych experiment as the desired excuse. I've since lost the actual report I turned in, which was very detailed, probably the longest document I wrote in high school:



**Purpose**: Mind control gamers.

**Hypothesis**: Flashing subliminal text in video games can influence a viewer's behavior.

**Background**: The internet holes I was de1ving were staunchly divided on whether any sort of subliminal messaging worked. I would settle it once and for all.

**Procedure**: I paid student volunteers 50c to play the game and take a difficult, multiple choice test, purportedly both would evaluate their "strategic thinking" but really the game would show them subliminal messages telling them to select "A" and the test would see how many "A"s were guessed.

**Data**: I've lost the actual data tables. I think I got about 10 volunteers 5 control (no messages) and 5 actual (shown varieties of "Choose A!"). No effect was observed, in fact the control group chose "A" more than chance would suggest and the influenced group chose A less than chance would suggest.

**Conclusion**: I rambled for a couple pages about any possible thing that went gone wrong because that's what I thought real scientists did (they don't). One notable thing is that I had intended to do a double blind study, but failed to build a tutorial and I think that should have been a signal to myself about weaknesses in my game design and communication skills.

**Revisited Conclusion**: despite a 11th grader conclusively proving that no such effect exists, PHDs would continue to research the topic and in fact generally conclude that this effect is real: [Priming](https://en.wikipedia.org/wiki/Priming_%28psychology%29)



## The Code

Kept pretty much identical to the original implementation. Phones/tablets weren't even on the horizon for serious use, so I never thought how the controls might change for a touch screen.



I had a sense that things like the O(n^2) of ships looking for each other was inefficient, but could neither voice it (no complexity analysis classes yet) nor do any tricky algorithms to fix it (no algorithms classes yet). It ran just fine on all the computers I put it on, so no problem.



## The Demo

There's a Javascript version of this game here, because late 90s Java doesn't seem to agree with 2020s Java compilers and I don't have any java interpreters around anyway; they got removed from most browsers and I've given up getting one installed on windows. The JS code is the result of AI (OpenAI's Codex) translating the Java code to JS; I was going to do this by hand for no reason: I already got the experience of translating a couple of my other early [Java game](https://github.com/TealCoder/Tyrian)s to Javascript in the pre-AI era. I don't expect this game to garnish enough fame that will require me to sheepishly admit that the translation was done by AI. An further, **I was compiling this to java bytecode by machine, why shouldn't I compile it to javascript by machine?**



## Maybe Someday

* Design a tutorial level; make AI implement it, because its my communication that needs work, not the tediousness of typing in map coordinates. Might make some of the controls more obvious.
* I always wanted sound effects and music; might actually happen.
* Re-organize with my now profesional level java skillz (not happening but written on every note I ever made about this game since about 2005).
* And add all the things I avoided when first implementing: collision detection, rotating portraits, obvious directionality of laser beams (maybe even torpidos?).

