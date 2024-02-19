import React from 'react';

export const Comment = ({ author, content, date, likes }) => {
  return (
    <div className="comment">
      <strong>{author}:</strong> {content}
    </div>
  );
};
