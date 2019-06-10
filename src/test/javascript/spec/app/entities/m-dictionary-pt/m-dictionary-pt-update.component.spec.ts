/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryPtUpdateComponent } from 'app/entities/m-dictionary-pt/m-dictionary-pt-update.component';
import { MDictionaryPtService } from 'app/entities/m-dictionary-pt/m-dictionary-pt.service';
import { MDictionaryPt } from 'app/shared/model/m-dictionary-pt.model';

describe('Component Tests', () => {
  describe('MDictionaryPt Management Update Component', () => {
    let comp: MDictionaryPtUpdateComponent;
    let fixture: ComponentFixture<MDictionaryPtUpdateComponent>;
    let service: MDictionaryPtService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryPtUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDictionaryPtUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDictionaryPtUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryPtService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDictionaryPt(123);
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
        const entity = new MDictionaryPt();
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
