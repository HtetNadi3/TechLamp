package dao.bookmark;

import java.util.List;

import entity.Bookmark;

public interface BookmarkDao {
    void dbAddBookmark(Bookmark bookmark);

    List<Bookmark> dbGetAllBookmarkByUserId(int userId);

    void dbDeleteById(int id);
}
