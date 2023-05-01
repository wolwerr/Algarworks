import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormOfertaComponent } from './form-oferta.component';

describe('FormOfertaComponent', () => {
  let component: FormOfertaComponent;
  let fixture: ComponentFixture<FormOfertaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormOfertaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormOfertaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
