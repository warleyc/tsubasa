/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetTeamGroupUpdateComponent } from 'app/entities/m-target-team-group/m-target-team-group-update.component';
import { MTargetTeamGroupService } from 'app/entities/m-target-team-group/m-target-team-group.service';
import { MTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';

describe('Component Tests', () => {
  describe('MTargetTeamGroup Management Update Component', () => {
    let comp: MTargetTeamGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetTeamGroupUpdateComponent>;
    let service: MTargetTeamGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetTeamGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetTeamGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetTeamGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetTeamGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetTeamGroup(123);
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
        const entity = new MTargetTeamGroup();
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
