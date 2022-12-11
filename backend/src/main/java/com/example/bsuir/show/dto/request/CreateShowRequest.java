package com.example.bsuir.show.dto.request;

import com.example.bsuir.shared.dto.request.Request;
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
public class CreateShowRequest implements Request<Show> {

    private String name;

    private String description;

    private ShowType showType;
}
