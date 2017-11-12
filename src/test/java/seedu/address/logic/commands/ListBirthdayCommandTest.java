package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.BirthdayChecker;

//@@author hymss
/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListBirthdayCommandTest {

    private Model model;
    private Model expectedModel;
    private ListBirthdayCommand listBirthdayCommand;
    private BirthdayChecker checker = new BirthdayChecker();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        listBirthdayCommand = new ListBirthdayCommand();
        listBirthdayCommand.setData(model, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void executeListBirthdaySuccess() {
        ListBirthdayCommand listBirthdayCommand = prepareCommand();
        expectedModel.updateFilteredPersonList(checker);
        assertCommandSuccess(listBirthdayCommand, model, listBirthdayCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     *  This filters Contags to show persons whose birthdays fall on the current date.
     */
    private ListBirthdayCommand prepareCommand() {
        ListBirthdayCommand command = new ListBirthdayCommand();
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }
}
