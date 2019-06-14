/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetActionGroupUpdateComponent } from 'app/entities/m-target-action-group/m-target-action-group-update.component';
import { MTargetActionGroupService } from 'app/entities/m-target-action-group/m-target-action-group.service';
import { MTargetActionGroup } from 'app/shared/model/m-target-action-group.model';

describe('Component Tests', () => {
  describe('MTargetActionGroup Management Update Component', () => {
    let comp: MTargetActionGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetActionGroupUpdateComponent>;
    let service: MTargetActionGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetActionGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetActionGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetActionGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetActionGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetActionGroup(123);
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
        const entity = new MTargetActionGroup();
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
