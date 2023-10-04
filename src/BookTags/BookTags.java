package BookTags;

public class BookTags {
   private int goodreadsBookId,tagId,count;

   public BookTags(int goodreadsBookId, int tagId, int count) {
      this.goodreadsBookId = goodreadsBookId;
      this.tagId = tagId;
      this.count = count;
   }

   public int getGoodreadsBookId() {
      return goodreadsBookId;
   }

   public void setGoodreadsBookId(int goodreadsBookId) {
      this.goodreadsBookId = goodreadsBookId;
   }

   public int getTagId() {
      return tagId;
   }

   public void setTagId(int tagId) {
      this.tagId = tagId;
   }

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }
}
