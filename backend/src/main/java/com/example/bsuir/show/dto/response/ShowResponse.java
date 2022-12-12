package com.example.bsuir.show.dto.response;

import com.example.bsuir.shared.dto.response.Response;
import com.example.bsuir.show.model.Show;
import com.example.bsuir.show.model.ShowType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponse implements Response<Show> {

    private Long id;

    private String name;

    private String description;

    private ShowType showType;
}
