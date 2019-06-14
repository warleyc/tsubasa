/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildNegativeWordUpdateComponent } from 'app/entities/m-guild-negative-word/m-guild-negative-word-update.component';
import { MGuildNegativeWordService } from 'app/entities/m-guild-negative-word/m-guild-negative-word.service';
import { MGuildNegativeWord } from 'app/shared/model/m-guild-negative-word.model';

describe('Component Tests', () => {
  describe('MGuildNegativeWord Management Update Component', () => {
    let comp: MGuildNegativeWordUpdateComponent;
    let fixture: ComponentFixture<MGuildNegativeWordUpdateComponent>;
    let service: MGuildNegativeWordService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildNegativeWordUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuildNegativeWordUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuildNegativeWordUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildNegativeWordService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuildNegativeWord(123);
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
        const entity = new MGuildNegativeWord();
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
