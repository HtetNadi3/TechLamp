package service.bookmark;

import java.util.List;

import dto.bookmark.BookmarkDTO;

public interface BookmarkService {
    void doAddBookmark(BookmarkDTO bookmarkDto);

    List<BookmarkDTO> doGetAllBookmarkByUserId(int userId);

    void doDeleteById(int id);
}
