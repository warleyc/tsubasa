/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryEnUpdateComponent } from 'app/entities/m-dictionary-en/m-dictionary-en-update.component';
import { MDictionaryEnService } from 'app/entities/m-dictionary-en/m-dictionary-en.service';
import { MDictionaryEn } from 'app/shared/model/m-dictionary-en.model';

describe('Component Tests', () => {
  describe('MDictionaryEn Management Update Component', () => {
    let comp: MDictionaryEnUpdateComponent;
    let fixture: ComponentFixture<MDictionaryEnUpdateComponent>;
    let service: MDictionaryEnService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryEnUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDictionaryEnUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDictionaryEnUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryEnService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDictionaryEn(123);
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
        const entity = new MDictionaryEn();
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
