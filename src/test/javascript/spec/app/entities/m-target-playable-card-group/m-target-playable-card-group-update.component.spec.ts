/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetPlayableCardGroupUpdateComponent } from 'app/entities/m-target-playable-card-group/m-target-playable-card-group-update.component';
import { MTargetPlayableCardGroupService } from 'app/entities/m-target-playable-card-group/m-target-playable-card-group.service';
import { MTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';

describe('Component Tests', () => {
  describe('MTargetPlayableCardGroup Management Update Component', () => {
    let comp: MTargetPlayableCardGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetPlayableCardGroupUpdateComponent>;
    let service: MTargetPlayableCardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetPlayableCardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetPlayableCardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetPlayableCardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetPlayableCardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetPlayableCardGroup(123);
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
        const entity = new MTargetPlayableCardGroup();
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
