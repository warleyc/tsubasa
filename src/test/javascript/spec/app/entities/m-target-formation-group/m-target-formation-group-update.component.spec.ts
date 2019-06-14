/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetFormationGroupUpdateComponent } from 'app/entities/m-target-formation-group/m-target-formation-group-update.component';
import { MTargetFormationGroupService } from 'app/entities/m-target-formation-group/m-target-formation-group.service';
import { MTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';

describe('Component Tests', () => {
  describe('MTargetFormationGroup Management Update Component', () => {
    let comp: MTargetFormationGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetFormationGroupUpdateComponent>;
    let service: MTargetFormationGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetFormationGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetFormationGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetFormationGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetFormationGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetFormationGroup(123);
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
        const entity = new MTargetFormationGroup();
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
