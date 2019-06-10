/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryArUpdateComponent } from 'app/entities/m-dictionary-ar/m-dictionary-ar-update.component';
import { MDictionaryArService } from 'app/entities/m-dictionary-ar/m-dictionary-ar.service';
import { MDictionaryAr } from 'app/shared/model/m-dictionary-ar.model';

describe('Component Tests', () => {
  describe('MDictionaryAr Management Update Component', () => {
    let comp: MDictionaryArUpdateComponent;
    let fixture: ComponentFixture<MDictionaryArUpdateComponent>;
    let service: MDictionaryArService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryArUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDictionaryArUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDictionaryArUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryArService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDictionaryAr(123);
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
        const entity = new MDictionaryAr();
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
