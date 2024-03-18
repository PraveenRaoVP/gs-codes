package com.librarymanagement.AdminManagement;

import com.librarymanagement.models.Admin;
import com.librarymanagement.repository.AdminDatabase;
import com.librarymanagement.repository.AdminToLibraryDatabase;

import java.util.List;

public class AdminManagerModel {
    private final AdminManagerView adminManagerView;

    public AdminManagerModel(AdminManagerView adminManagerView) {
        this.adminManagerView = adminManagerView;
    }

    public void addAdmin(int libraryId, String name, String email, String address) {
        int adminId = AdminDatabase.getInstance().getAdminList().size()+1;
        Admin admin = new Admin(adminId, name, email, address);
        AdminDatabase.getInstance().insertAdmin(admin);
        AdminToLibraryDatabase.getInstance().addLibraryAdmin(libraryId, adminId);
        adminManagerView.showAlert("Admin added successfully");
    }

    public void removeAdmin(int adminId) {
        AdminDatabase.getInstance().deleteAdmin(adminId);
        adminManagerView.showAlert("Admin removed successfully");
    }

    public void updateAdmin(int adminId, String name, String email, String address) {
        AdminDatabase.getInstance().updateAdmin(adminId, name, email, address);
        adminManagerView.showAlert("Admin updated successfully");
    }

    public List<Admin> getAllAdminList() {
        return AdminDatabase.getInstance().getAdminList();
    }

//    public List<Admin> getAdminsForLibrary(int libraryId) {
//        List<Integer> adminIds = AdminToLibraryDatabase.getInstance().getAdminsForLibrary(libraryId);
//        List<Admin> adminList = AdminDatabase.getInstance().getAdminList();
//        for(int i=0; i<adminList.size(); i++) {
//            if(!adminIds.contains(adminList.get(i).getAdminId())) {
//                adminList.remove(i);
//                i--;
//            }
//        }
//        return adminList;
//    }

    public Admin getAdminForLibrary(int libraryId) {
        int adminId = AdminToLibraryDatabase.getInstance().getAdminByLibraryId(libraryId);
        return AdminDatabase.getInstance().getAdmin(adminId);
    }
}
