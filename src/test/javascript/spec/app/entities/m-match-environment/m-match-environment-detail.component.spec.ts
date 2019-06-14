/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchEnvironmentDetailComponent } from 'app/entities/m-match-environment/m-match-environment-detail.component';
import { MMatchEnvironment } from 'app/shared/model/m-match-environment.model';

describe('Component Tests', () => {
  describe('MMatchEnvironment Management Detail Component', () => {
    let comp: MMatchEnvironmentDetailComponent;
    let fixture: ComponentFixture<MMatchEnvironmentDetailComponent>;
    const route = ({ data: of({ mMatchEnvironment: new MMatchEnvironment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchEnvironmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMatchEnvironmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMatchEnvironmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMatchEnvironment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
