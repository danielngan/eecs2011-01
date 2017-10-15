package eecs2011.assignment1.banking;
import java.util.*;

public class AccountLog {
	
	private List<AccountActivity> activityRecords = new ArrayList<AccountActivity>();
		
	public AccountLog() {
		
	}
	
	public void addRecord(AccountActivity record) {
		activityRecords.add(record);
	}
	
	public int getRecordCount() {
		return activityRecords.size();
	}
	
	public AccountActivity getRecord(int index) {
		return activityRecords.get(index);
	}
	
	public void sortAccountLog() {
		List<AccountActivity> sortedRecords = new ArrayList<AccountActivity>();
		int size = activityRecords.size();
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
				if (result < 0) {
					sortedRecords.add(targetIndex, record);
					inserted = true;
					break;
				}
			}
			if (!inserted) {
				sortedRecords.add(record);
			}
		}
		this.activityRecords = sortedRecords;
	}
	
}
