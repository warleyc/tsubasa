/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryEsUpdateComponent } from 'app/entities/m-dictionary-es/m-dictionary-es-update.component';
import { MDictionaryEsService } from 'app/entities/m-dictionary-es/m-dictionary-es.service';
import { MDictionaryEs } from 'app/shared/model/m-dictionary-es.model';

describe('Component Tests', () => {
  describe('MDictionaryEs Management Update Component', () => {
    let comp: MDictionaryEsUpdateComponent;
    let fixture: ComponentFixture<MDictionaryEsUpdateComponent>;
    let service: MDictionaryEsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryEsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDictionaryEsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDictionaryEsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDictionaryEsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDictionaryEs(123);
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
        const entity = new MDictionaryEs();
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
