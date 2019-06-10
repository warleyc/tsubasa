/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryFrUpdateComponent } from 'app/entities/m-dictionary-fr/m-dictionary-fr-update.component';
import { MDictionaryFrService } from 'app/entities/m-dictionary-fr/m-dictionary-fr.service';
import { MDictionaryFr } from 'app/shared/model/m-dictionary-fr.model';

describe('Component Tests', () => {
  describe('MDictionaryFr Management Update Component', () => {
    let comp: MDictionaryFrUpdateComponent;
    let fixture: ComponentFixture<MDictionaryFrUpdateComponent>;
    let service: MDictionaryFrService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryFrUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDictionaryFrUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDictionaryFrUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryFrService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDictionaryFr(123);
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
        const entity = new MDictionaryFr();
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
