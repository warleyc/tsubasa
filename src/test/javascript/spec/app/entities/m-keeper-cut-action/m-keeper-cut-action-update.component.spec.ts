/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MKeeperCutActionUpdateComponent } from 'app/entities/m-keeper-cut-action/m-keeper-cut-action-update.component';
import { MKeeperCutActionService } from 'app/entities/m-keeper-cut-action/m-keeper-cut-action.service';
import { MKeeperCutAction } from 'app/shared/model/m-keeper-cut-action.model';

describe('Component Tests', () => {
  describe('MKeeperCutAction Management Update Component', () => {
    let comp: MKeeperCutActionUpdateComponent;
    let fixture: ComponentFixture<MKeeperCutActionUpdateComponent>;
    let service: MKeeperCutActionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MKeeperCutActionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MKeeperCutActionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MKeeperCutActionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MKeeperCutActionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MKeeperCutAction(123);
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
        const entity = new MKeeperCutAction();
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
