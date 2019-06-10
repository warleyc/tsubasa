/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCharacterBookUpdateComponent } from 'app/entities/m-character-book/m-character-book-update.component';
import { MCharacterBookService } from 'app/entities/m-character-book/m-character-book.service';
import { MCharacterBook } from 'app/shared/model/m-character-book.model';

describe('Component Tests', () => {
  describe('MCharacterBook Management Update Component', () => {
    let comp: MCharacterBookUpdateComponent;
    let fixture: ComponentFixture<MCharacterBookUpdateComponent>;
    let service: MCharacterBookService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCharacterBookUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCharacterBookUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCharacterBookUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCharacterBookService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCharacterBook(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCharacterBook();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
