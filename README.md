# Deduplication-algorithms
Complexity of different deduplication algorithms explored

Course: Intoduction to Data Structures

Background:
In the United States, in order to vote in elections you must be registered to vote. States must regularly update these voting rolls in order to account for deaths and newly registered voters. Some states additionally regularly audit their voting rolls and remove voters based on various rules – many of these rules have been criticized for being unnecessarily harsh and purposefully suppressing the vote. In the 2018 election in Georgia, an “exact match” rule was instituted to require a voter’s name as listed on their government-issued ID (e.g., their drivers license) to exactly match their name as listed on the voting rolls. Voters whose names did not match exactly were removed from the voting rolls.

Assignment Oveview:
- Implemented a naive equality method that could prevent people with the same name from voting. This can happen even if we consider many fields - for example, two people could share both a name and an address if they are members of the same family.
-Stored data and parsed it into an ArrayList containing the objects developed in the previous section (not deduplicated yet)
-Deduplication Methods to exlore:


1)All Pairs Deduplication


2)Hash Table Deduplication


3)QuickSort Deduplication


-Explored time complexity of above algorithms and graphed the results in java (deduplication.png)showing the number of rows deduplicated on the x-axis and the runtime (in seconds) on the y-axis for each of these methods 
-Read in any CSV file from the command line input, deduplicate the data and print out the results formatted as:

Records given:2000


Attributes checked:X,Y,Z


Duplicates found:100
