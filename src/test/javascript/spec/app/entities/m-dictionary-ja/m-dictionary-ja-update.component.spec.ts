/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryJaUpdateComponent } from 'app/entities/m-dictionary-ja/m-dictionary-ja-update.component';
import { MDictionaryJaService } from 'app/entities/m-dictionary-ja/m-dictionary-ja.service';
import { MDictionaryJa } from 'app/shared/model/m-dictionary-ja.model';

describe('Component Tests', () => {
  describe('MDictionaryJa Management Update Component', () => {
    let comp: MDictionaryJaUpdateComponent;
    let fixture: ComponentFixture<MDictionaryJaUpdateComponent>;
    let service: MDictionaryJaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryJaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDictionaryJaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDictionaryJaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryJaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDictionaryJa(123);
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
        const entity = new MDictionaryJa();
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
