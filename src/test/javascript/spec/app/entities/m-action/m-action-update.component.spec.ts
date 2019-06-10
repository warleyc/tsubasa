/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionUpdateComponent } from 'app/entities/m-action/m-action-update.component';
import { MActionService } from 'app/entities/m-action/m-action.service';
import { MAction } from 'app/shared/model/m-action.model';

describe('Component Tests', () => {
  describe('MAction Management Update Component', () => {
    let comp: MActionUpdateComponent;
    let fixture: ComponentFixture<MActionUpdateComponent>;
    let service: MActionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MActionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MActionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAction(123);
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
        const entity = new MAction();
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
