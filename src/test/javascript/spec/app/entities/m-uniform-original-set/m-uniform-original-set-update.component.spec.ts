/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformOriginalSetUpdateComponent } from 'app/entities/m-uniform-original-set/m-uniform-original-set-update.component';
import { MUniformOriginalSetService } from 'app/entities/m-uniform-original-set/m-uniform-original-set.service';
import { MUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';

describe('Component Tests', () => {
  describe('MUniformOriginalSet Management Update Component', () => {
    let comp: MUniformOriginalSetUpdateComponent;
    let fixture: ComponentFixture<MUniformOriginalSetUpdateComponent>;
    let service: MUniformOriginalSetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformOriginalSetUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MUniformOriginalSetUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MUniformOriginalSetUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MUniformOriginalSetService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MUniformOriginalSet(123);
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
        const entity = new MUniformOriginalSet();
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
