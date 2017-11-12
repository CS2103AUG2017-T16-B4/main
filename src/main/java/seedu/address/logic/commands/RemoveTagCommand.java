package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_REMOVE_TAG_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_TAG_NOT_FOUND;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Level;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * Deletes a tag from the entire AddressBook
 */
//@@author justuswah
public class RemoveTagCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "removeTag";
    public static final String COMMAND_ALIAS = "rt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the specified tag from all contacts in the AddressBook\n"
            + "Parameters: (TAG_NAME) \n"
            + "Example: " + COMMAND_WORD + " friends";
    private final Tag toRemove;

    public RemoveTagCommand(Tag toRemove) {
        this.toRemove = toRemove;
    }

    /**Searches the entire AddressBook for the {@Code toRemove Tag}
     *Returns either "Tag Removed" or "Tag not found", depending on data
     *
     */

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);

        if (!model.getAddressBook().getTagList().contains(toRemove)) {
            throw new TagNotFoundException(String.format(MESSAGE_TAG_NOT_FOUND));
        } else {
            model.removeTag(toRemove);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            logger.log(Level.FINE, "Tag " + toRemove.tagName + " Removed");
            return new CommandResult(String.format(MESSAGE_REMOVE_TAG_SUCCESS));
        }
    }


}
