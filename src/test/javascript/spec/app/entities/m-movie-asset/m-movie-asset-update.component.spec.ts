/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMovieAssetUpdateComponent } from 'app/entities/m-movie-asset/m-movie-asset-update.component';
import { MMovieAssetService } from 'app/entities/m-movie-asset/m-movie-asset.service';
import { MMovieAsset } from 'app/shared/model/m-movie-asset.model';

describe('Component Tests', () => {
  describe('MMovieAsset Management Update Component', () => {
    let comp: MMovieAssetUpdateComponent;
    let fixture: ComponentFixture<MMovieAssetUpdateComponent>;
    let service: MMovieAssetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMovieAssetUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMovieAssetUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMovieAssetUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMovieAssetService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMovieAsset(123);
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
        const entity = new MMovieAsset();
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
