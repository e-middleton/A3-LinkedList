# A7 Linked Lists

Your readme should include the following information. **Each student** needs to submit all of this information, even when pair programming. 

## Submission Details

Programming Partner Name (if you are submitting identical code): na


Other Collaborators (submitting related but non-identical code): na


Kudos/shout-out to particularly helpful members of the class or teaching staff: Shout out to Prof. Howe for answering my 101 questions during office hours.


Any references used besides JavaDoc and course materials: na


If you used AI, please describe how and the interaction between AI and your understanding of this assignment and specifically the related data structures and algorithms: na

## Reflection

What do you see as the benefits and downsides of working with linked lists instead of arrays? When might they be most useful versus limiting?

Linked lists are more useful for when elements are being frequently inserted and deleted, because reallocating memory for arrays is costly in this operation, and cheap in linked lists. 
But linked lists are more costly when an item needs to be accessed. So if the data structure is not frequently changed, but is used for accessing data, an array would be more appropriate. 
For transfer style methods, linked lists seem more useful when splicing and extracting, but if the method is a copy method, it does not save time compared to using an array implementation, because they both require looping through the elements. 
I like how convient the transfer style becomes with linked lists, but I feel like I would personally become tripped up and assume they were using a copy style when they weren't, because I'm so accustomed to methods being copy style. But using copy-style methods seems costly no matter what the implementation is, linked lists or dynamic arrays.


What was the major challenge you faced in completing this assignment?
I had a lot of trouble thinking through my implementaiton of the Iterator, because the head was sometimes not pointing where it should have been, and thus changing the wrong elements. It was helpful to go to office hours and draw things out on the whiteboard several times.


What do you feel you learned from this assignment?
Using a whiteboard or drawing things out in my notebook was helpful throughout the whole implementation because it wasn't always immediately clear to me where nodes were pointing, or where a loop would end up after completion.
I feel like I have a more complete view of the different options (linked lists or dynamic arrays) for implementation of an object that seems similar on the user end but differs in memory usage.
This is also the first time I've implemented or used an iterator, so that was fun for me. I've been avoiding it because it felt unfamiliar, but I'm really glad that I took the time to try it out on this assignment!


note: There is no way to include an informative error message in the exceptions like the checklists requests.

