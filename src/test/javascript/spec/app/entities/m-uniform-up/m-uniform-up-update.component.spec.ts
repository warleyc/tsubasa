/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformUpUpdateComponent } from 'app/entities/m-uniform-up/m-uniform-up-update.component';
import { MUniformUpService } from 'app/entities/m-uniform-up/m-uniform-up.service';
import { MUniformUp } from 'app/shared/model/m-uniform-up.model';

describe('Component Tests', () => {
  describe('MUniformUp Management Update Component', () => {
    let comp: MUniformUpUpdateComponent;
    let fixture: ComponentFixture<MUniformUpUpdateComponent>;
    let service: MUniformUpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformUpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MUniformUpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MUniformUpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MUniformUpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MUniformUp(123);
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
        const entity = new MUniformUp();
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
