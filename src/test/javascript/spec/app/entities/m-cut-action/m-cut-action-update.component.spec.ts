/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCutActionUpdateComponent } from 'app/entities/m-cut-action/m-cut-action-update.component';
import { MCutActionService } from 'app/entities/m-cut-action/m-cut-action.service';
import { MCutAction } from 'app/shared/model/m-cut-action.model';

describe('Component Tests', () => {
  describe('MCutAction Management Update Component', () => {
    let comp: MCutActionUpdateComponent;
    let fixture: ComponentFixture<MCutActionUpdateComponent>;
    let service: MCutActionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutActionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCutActionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCutActionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCutActionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCutAction(123);
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
        const entity = new MCutAction();
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
