/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetActionTypeGroupUpdateComponent } from 'app/entities/m-target-action-type-group/m-target-action-type-group-update.component';
import { MTargetActionTypeGroupService } from 'app/entities/m-target-action-type-group/m-target-action-type-group.service';
import { MTargetActionTypeGroup } from 'app/shared/model/m-target-action-type-group.model';

describe('Component Tests', () => {
  describe('MTargetActionTypeGroup Management Update Component', () => {
    let comp: MTargetActionTypeGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetActionTypeGroupUpdateComponent>;
    let service: MTargetActionTypeGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetActionTypeGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetActionTypeGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetActionTypeGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetActionTypeGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetActionTypeGroup(123);
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
        const entity = new MTargetActionTypeGroup();
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
