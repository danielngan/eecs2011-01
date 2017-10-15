package eecs2011.assignment1.banking;
import java.util.*;

public class AccountActivityLog {
	
	private List<AccountActivity> activityRecords = new ArrayList<AccountActivity>();
		
	public AccountActivityLog() {
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
	
	public void sort() {
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
	
}
