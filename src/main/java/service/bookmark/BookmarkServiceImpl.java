package service.bookmark;

import java.util.List;
import java.util.stream.Collectors;

import dao.bookmark.BookmarkDao;
import dao.bookmark.BookmarkDaoImpl;
import dto.bookmark.BookmarkDTO;
import entity.Bookmark;

public class BookmarkServiceImpl implements BookmarkService {

    private BookmarkDao bookmarkDao = new BookmarkDaoImpl();

    @Override
    public void doAddBookmark(BookmarkDTO bookmarkDto) {
        bookmarkDao.dbAddBookmark(new Bookmark(bookmarkDto));
    }

    @Override
    public List<BookmarkDTO> doGetAllBookmarkByUserId(int userId) {
        return bookmarkDao.dbGetAllBookmarkByUserId(userId).stream().map(bookmark -> new BookmarkDTO(bookmark))
                .collect(Collectors.toList());
    }

    @Override
    public void doDeleteById(int id) {
        bookmarkDao.dbDeleteById(id);
    }
}
