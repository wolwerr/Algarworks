import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableOfertaComponent } from './table-oferta.component';

describe('TableClassComponent', () => {
  let component: TableOfertaComponent;
  let fixture: ComponentFixture<TableOfertaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableOfertaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableOfertaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
