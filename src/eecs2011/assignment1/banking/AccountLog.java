package eecs2011.assignment1.banking;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * 
 * @author Daniel, Brian
 *
 */
public class AccountLog {
	
	private List<AccountActivity> activityRecords = new ArrayList<AccountActivity>();
		
	public AccountLog(List<AccountActivity> activityRecords) {
	    this.activityRecords = activityRecords;
	}
    
	public AccountLog() {
	    this(new ArrayList<AccountActivity>());
	}

	public void addRecord(AccountActivity record) {
		activityRecords.add(record);
	}
	
	public int getNumberOfRecords() {
		return activityRecords.size();
	}
	
	public AccountActivity getRecord(int index) {
		return activityRecords.get(index);
	}
	
	public void sortAccountLog() {
	    // create a new list to store the sorted records
		List<AccountActivity> sortedRecords = new ArrayList<AccountActivity>();
		int size = activityRecords.size();
		// insertion sort to insert records from the original list to the
		// new list
		for (int index = 0; index < size; index++) {
			AccountActivity record = activityRecords.get(index);
			int targetSize = sortedRecords.size();
			boolean inserted = false;
			for (int targetIndex = 0; targetIndex < targetSize; targetIndex++) {
				AccountActivity targetRecord = sortedRecords.get(targetIndex);
				int result = record.getAccountSIN().compareTo(targetRecord.getAccountSIN());
				if (result == 0) {
					result = record.getActivityDateTime().compareTo(targetRecord.getActivityDateTime());
				}
				if (result < 0) {   // insert the record if it it is less than the sorted one
					sortedRecords.add(targetIndex, record);
					inserted = true;
					break;
				}
			}
			// if the inserted flag is not set, it means the current record is larger
			// than anyone in the sorted list, then append the record to the end
			// of the sorted list
			if (!inserted) {
				sortedRecords.add(record);
			}
		}
		// finally the new list contains records in sorted order; the original list
		// is then replaced by the newly sorted list
		this.activityRecords = sortedRecords;
	}
	
	public List<AccountActivity> getActivityRecords() {
	    return activityRecords;
	}
	
	public void saveAccountLog(String accountLogFile) throws IOException{
		FileOutputStream output = new FileOutputStream(accountLogFile);
		ObjectOutputStream stream = new ObjectOutputStream(output);
		stream.writeObject(activityRecords);
		stream.close();
	}
	
	public void loadAccountLog(String accountLogFile) throws IOException, ClassNotFoundException{
		FileInputStream input = new FileInputStream(accountLogFile);
		ObjectInputStream stream = new ObjectInputStream(input);
		List<AccountActivity>activityRecords = (ArrayList<AccountActivity>) stream.readObject();
		stream.close();
	}
}
